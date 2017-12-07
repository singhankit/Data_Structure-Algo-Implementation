package ds_sorting;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ESPTestClientForGen {

	static String genUsername= "espapi.gen";
	static String genPassword = "apiesp";
	static String snEndpointReference = "https://ciscodev.service-now.com";
	
	static String grantType = "urn:ietf:params:oauth:grant-type:saml2-bearer";

    private String urlSAML = "https://cloudsso-test.cisco.com/idp/sts.wst";
    private String snTokenUrl = "https://ciscodev.service-now.com/saml_bearer_oauth_token.do";
    private String snEndpointUrl = "https://ciscodev.service-now.com/api/now/table/incident?sysparm_fields=number";
    private RestTemplate restTemplate = getRestTemplate();
    private RestTemplate restTemplateFollowRedirect = getRestTemplateFollowRedirect();

	public static void main(String[] args) {
		ESPTestClientForGen obj = new ESPTestClientForGen();
		String key = "1";
		switch (key) {
		case "1" :

			String postResp = obj.getSAMLResponse(genUsername, genPassword, snEndpointReference);
			System.out.println("******postResp*******");
			System.out.println(postResp);
			System.out.println("**************");

			String securityToken = obj.extractSecurityToken(postResp);
	        System.out.println(securityToken);
	        System.out.println("**************");

			String finalResp = obj.getFinalAccessToken(securityToken);
			System.out.println("******finalResp*******");
			System.out.println(finalResp);
			System.out.println("**************");

			Map<String, String> tokenMapSN = obj.mapResponse(finalResp);

			String accessTokenSN = tokenMapSN.get("access_token");
			System.out.println("******accessToken*******");
			System.out.println(accessTokenSN);
			System.out.println("**************");

			String listData = obj.createIncident(accessTokenSN);
			System.out.println("******listData*******");
			System.out.println(listData);
			System.out.println("**************");

			break;
		default:
			break;
		}
	}

	private Map<String, String> mapResponse(String accessTokenResp) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> tokenMap = null;
		try {
			tokenMap = mapper.readValue(accessTokenResp, new TypeReference<Map<String, String>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenMap;
	}

    private String getFinalAccessToken(String securityToken) {
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("assertion", securityToken);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(snTokenUrl).queryParams(params);
        URI listUrl = builder.build().toUri();

        HttpEntity<String> request = new HttpEntity<>(headers);

        // First Stage
        ResponseEntity<String> results = null;
		try {
			results = restTemplateFollowRedirect.exchange(listUrl, HttpMethod.GET, request, String.class);

		} catch (HttpStatusCodeException ex) {
			System.out.println(ex.getResponseBodyAsString());
		} catch (RestClientException e) {
			e.printStackTrace();
		}
        System.out.println(results);
        return results.getBody();
    }

   private String getSAMLResponse(String username, String password, String endpointReference) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + getBase64Value());
        headers.add("Content-Type", "application/soap+xml");

        HttpEntity<String> request = new HttpEntity<String>(getTokenSOAPXML(username, password, endpointReference), headers);

        // First Stage
        ResponseEntity<String> results = null;
		try {
			results = restTemplate.exchange(urlSAML, HttpMethod.POST, request, String.class);
		} catch (HttpStatusCodeException ex) {
			System.out.println(ex.getResponseBodyAsString());
		} catch (RestClientException e) {
			e.printStackTrace();
		}
        return results.getBody();
    }

   private String createIncident(String accessToken) {
       HttpHeaders headers = new HttpHeaders();
       headers.add("Authorization", "Bearer " + accessToken);
       headers.add("Content-Type", "application/json");
       headers.add("Accept", "application/json");
       
       JSONObject obj = new JSONObject();
		
		obj.put("u_business_service", "Oracle Database");
		obj.put("caller_id", "jimipate");
		obj.put("location", "RTP 5 - CREEKSIDE");
		obj.put("category", "Access");
		obj.put("sub_category", "Authentication");
		obj.put("impact", "3");
		obj.put("urgency", "3");
		obj.put("short_description", "Test Incident");
		obj.put("description", "Test Incident");
		obj.put("contact_type", "Phone");
		obj.put("comments", "Hello 123&%^&$");
		obj.put("cmdb_ci", "");

       HttpEntity<String> request = new HttpEntity<String>(obj.toString(), headers);

       // First Stage
       ResponseEntity<String> results = null;
		try {
			results = restTemplate.exchange(snEndpointUrl, HttpMethod.POST, request, String.class);
		} catch (HttpStatusCodeException ex) {
			System.out.println(ex.getResponseBodyAsString());
		} catch (RestClientException e) {
			e.printStackTrace();
		}
       return results.getBody();
   }


    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                connection.setInstanceFollowRedirects(false);
            }
        });
        return restTemplate;
    }
    
    public static RestTemplate getRestTemplateFollowRedirect() {
  		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory() {
  			@Override
  			protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
  				super.prepareConnection(connection, httpMethod);
  				connection.setInstanceFollowRedirects(true);
  			}
  		});
  		return restTemplate;
  	}

    private String getTokenSOAPXML(String username, String password, String serviceURL) {
    	
    	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
			+ "<soap:Header>"
			+ "<wsa:Action xmlns:wsa=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"iJFJKoU2cJbfDgjZTwrmZKpS8nKM\">http://docs.oasis-open.org/ws-sx/ws-trust/200512/RST/Issue</wsa:Action>"
			+ "<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" soap:mustUnderstand=\"1\">"
			+ "<wsse:UsernameToken xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"ijhaEN438vD_gRUQC06Apb8B43nE\">"
			+ "<wsse:Username>" + username + "</wsse:Username>"
			+ "<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">" + password + "</wsse:Password>"
			+ "</wsse:UsernameToken>"
			+ "</wsse:Security>"
			+ "</soap:Header>"
			+ "<soap:Body xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"iGPI7F3OqQHNY_nNdIi6mxp16eqE\">"
			+ "<wst:RequestSecurityToken xmlns:wst=\"http://docs.oasis-open.org/ws-sx/ws-trust/200512/\">"
			+ "<wst:TokenType>urn:ietf:params:oauth:grant-type:saml2-bearer</wst:TokenType>"
			+ "<wst:RequestType>http://docs.oasis-open.org/ws-sx/ws-trust/200512/Issue</wst:RequestType>"
			+ "<wsp:AppliesTo xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\">"
			+ "<wsa:EndpointReference xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">"
			+ "<wsa:Address>" + serviceURL + "</wsa:Address>"
			+ "</wsa:EndpointReference>"
			+ "</wsp:AppliesTo>"
			+ "<wst:Claims/>"
			+ "<wst:OnBehalfOf>"
			+ "<wsse:SecurityTokenReference xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"
			+ "<wsse:Reference URI=\"#ijhaEN438vD_gRUQC06Apb8B43nE\" ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#UsernameToken\"/>"
			+ "</wsse:SecurityTokenReference>"
			+ "</wst:OnBehalfOf>"
			+ "</wst:RequestSecurityToken>"
			+ "</soap:Body>"
			+ "</soap:Envelope>";
    }

    private String getBase64Value() {
    	return new String(Base64.encodeBase64(("tokenxchg-esp" + ":" + "123Cisco!").getBytes()));
    }

    private String extractSecurityToken(String soapResp) {
    	String retVal = null;
    	try {
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder parser = factory.newDocumentBuilder();
    		Document document = parser.parse(new ByteArrayInputStream(soapResp.getBytes()));
    		NodeList list=document.getElementsByTagName("wsse:BinarySecurityToken");
    		Node node=list.item(0);
    		System.out.println("SecurityToken: " + node.getTextContent());
    		retVal = node.getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return retVal;
    }
}