package com.fiveguys.koguma.service.common;


import java.util.ArrayList;
import java.util.List;

import com.fiveguys.koguma.data.dto.LocationDTO;
import com.fiveguys.koguma.data.entity.Location;
import com.fiveguys.koguma.repository.common.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{


    private final LocationRepository locationRepository;

    public List<LocationDTO> listLocation(Long id) {
        List<Location> locations = locationRepository.findAllById(id);
        List<LocationDTO> locationDTOList = new ArrayList<>();

        for (Location location : locations){
            LocationDTO locationDTO = LocationDTO.fromEntity(location);
            locationDTOList.add(locationDTO);
        }

        return locationDTOList;
    }


    @Transactional
    public void setRepLocation(Long id) {
        Location location = locationRepository.findById(id).orElse(null);
        if (location != null){
            Location beforeRepLocation = (Location) locationRepository.findByRepAuthLocationFlagIs(true);
            beforeRepLocation.setRepAuthLocationFlag(false);
            location.setRepAuthLocationFlag(true);
        }
    }


    public Location addLocation(LocationDTO locationDTO) {
        locationRepository.save(locationDTO.toEntity());

        return null;
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Transactional
    public void updateSearchRange(Long id,int range) {
        Location location = locationRepository.findById(id).orElse(null);
        locationRepository.findById(id).orElseThrow().setSearchRange(range);

    }

    public LocationDTO addShareLocation() {
        return null;
    }



//    public void location(){
//        final int timeout = 300000;
//        System.out.println("run 0");
//        final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
//        this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
//        System.out.println("run 1");
//        try {
//            this.run("14.52.169.241");  // 현재 객체의 run 메서드를 호출하도록 수정
//        } catch (final Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//        public void run(final String ip) throws Exception {
//            final String requestMethod = "GET";
//            final String hostName = "https://geolocation.apigw.fin-ntruss.com";
//            final String requestUrl= "/geolocation/v2/geoLocation";
//
//            final Map<String, List<String>> requestParameters = new HashMap<String, List<String>>();
//            requestParameters.put("ip", Arrays.asList(ip));
//            requestParameters.put("ext", Arrays.asList("t"));
//            requestParameters.put("responseFormatType", Arrays.asList("json"));
//
//            SortedMap<String, SortedSet<String>> parameters = convertTypeToSortedMap(requestParameters);
//
//            String timestamp = generateTimestamp();
//            System.out.println("timestamp: " + timestamp);
//
//            String baseString = requestUrl + "?" + getRequestQueryString(parameters);
//            System.out.println("baseString : " + baseString);
//
//            String signature = makeSignature(requestMethod, baseString, timestamp, accessKey, "PxZWLnZBdthl4W3bt79ItS1cb0JrKxCGorapBoMp");
//            System.out.println("signature : " + signature);
//
//            final String requestFullUrl = hostName + baseString;
//            final HttpGet request = new HttpGet(requestFullUrl);
//            request.setHeader("x-ncp-apigw-timestamp",timestamp);
//            request.setHeader("x-ncp-iam-access-key",accessKey);
//            request.setHeader("x-ncp-apigw-signature-v2",signature);
//            System.out.println("b");
//            final CloseableHttpResponse response;
//            response = httpClient.execute(request);
//
//            final String msg = getResponse(response);
//            System.out.println(msg);
//        }
//
//        private String getResponse(final CloseableHttpResponse response) throws Exception {
//            final StringBuffer buffer = new StringBuffer();
//            final BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//
//            String msg;
//
//            try {
//                while ((msg = reader.readLine()) != null) {
//                    buffer.append(msg);
//                }
//            } catch (final Exception e) {
//                throw e;
//            } finally {
//                response.close();
//            }
//            return buffer.toString();
//        }
//
//
//        private static SortedMap<String, SortedSet<String>> convertTypeToSortedMap(final Map<String, List<String>> requestParameters) {
//            final SortedMap<String, SortedSet<String>> significateParameters = new TreeMap<String, SortedSet<String>>();
//            final Iterator<String> parameterNames = requestParameters.keySet().iterator();
//            while (parameterNames.hasNext()) {
//                final String parameterName = parameterNames.next();
//                List<String> parameterValues = requestParameters.get(parameterName);
//                if (parameterValues == null) {
//                    parameterValues = new ArrayList<String>();
//                }
//
//                for (String parameterValue : parameterValues) {
//                    if (parameterValue == null) {
//                        parameterValue = "";
//                    }
//
//                    SortedSet<String> significantValues = significateParameters.get(parameterName);
//                    if (significantValues == null) {
//                        significantValues = new TreeSet<String>();
//                        significateParameters.put(parameterName, significantValues);
//                    }
//                    significantValues.add(parameterValue);
//                }
//
//            }
//            return significateParameters;
//        }
//
//        private static String generateTimestamp() {
//            return Long.toString(System.currentTimeMillis());
//        }
//
//        private static String getRequestQueryString(final SortedMap<String, SortedSet<String>> significantParameters) {
//            final StringBuilder queryString = new StringBuilder();
//            final Iterator<Map.Entry<String, SortedSet<String>>> paramIt = significantParameters.entrySet().iterator();
//            while (paramIt.hasNext()) {
//                final Map.Entry<String, SortedSet<String>> sortedParameter = paramIt.next();
//                final Iterator<String> valueIt = sortedParameter.getValue().iterator();
//                while (valueIt.hasNext()) {
//                    final String parameterValue = valueIt.next();
//
//                    queryString.append(sortedParameter.getKey()).append('=').append(parameterValue);
//
//                    if (paramIt.hasNext() || valueIt.hasNext()) {
//                        queryString.append('&');
//                    }
//                }
//            }
//            return queryString.toString();
//        }
//
//
//        public String makeSignature(final String method, final String baseString, final String timestamp, final String accessKey, final String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException{
//            String space = " ";                       // one space
//            String newLine = "\n";                    // new line
//
//            String message = new StringBuilder()
//                    .append(method)
//                    .append(space)
//                    .append(baseString)
//                    .append(newLine)
//                    .append(timestamp)
//                    .append(newLine)
//                    .append(accessKey)
//                    .toString();
//
//            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
//            Mac mac = Mac.getInstance("HmacSHA256");
//            mac.init(signingKey);
//            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
//            String encodeBase64String = Base64.encodeBase64String(rawHmac);
//            return encodeBase64String;
//        }
//    }

}

