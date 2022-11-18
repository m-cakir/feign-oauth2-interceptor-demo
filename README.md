# feign-oauth2-interceptor-demo
Feign Oauth2 Interceptor example

# If proxy is needed, add config to

## 1. Http-Client which is used by feign

## 2. RestOperations
````java
@Bean
public RestOperations oauth2ClientRestOperations() {

    RestTemplate restTemplate = new RestTemplate(Arrays.asList(
        new FormHttpMessageConverter(),
        new OAuth2AccessTokenResponseHttpMessageConverter(),
        new MappingJackson2HttpMessageConverter()));
    restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());

    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 3000));

    Authenticator authenticator = new Authenticator() {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication("username", "password".toCharArray()));
        }
    };

    Authenticator.setDefault(authenticator);
    requestFactory.setProxy(proxy);

    restTemplate.setRequestFactory(requestFactory);

    return restTemplate;
}

@Bean
public OAuth2AccessTokenResponseClient<OAuth2ClientCredentialsGrantRequest> clientCredentialsTokenResponseClient(RestOperations oauth2ClientRestOperations) {
    DefaultClientCredentialsTokenResponseClient tokenResponseClient = new DefaultClientCredentialsTokenResponseClient();
    tokenResponseClient.setRestOperations(oauth2ClientRestOperations);
    return tokenResponseClient;
}
````

