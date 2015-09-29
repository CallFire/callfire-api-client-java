How to build Callfire client:</br>
1. clone git repository</br>
2. execute ./gradlew clean build javadoc</br>
3. artifacts should appear in build/libs directory</br>
    - callfire-api-client-1.0.0-SNAPSHOT.jar - Callfire client without transitive dependencies</br>
    - callfire-api-client-1.0.0-SNAPSHOT-all.jar - client with all dependencies</br>
    - callfire-api-client-1.0.0-SNAPSHOT-javadoc.jar - client javadoc pages</br>
    - callfire-api-client-1.0.0-SNAPSHOT-sources.jar - client source code</br>

If you see such stacktrace:
<pre>
Exception in thread "main" com.callfire.api.client.CallfireClientException: javax.net.ssl.SSLProtocolException: handshake alert:  unrecognized_name
	at com.callfire.api.client.RestApiClient.get(RestApiClient.java:131)
	at com.callfire.api.client.RestApiClient.get(RestApiClient.java:92)
	at com.callfire.api.client.api.account.MeApi.getAccount(MeApi.java:64)
	at com.callfire.api.client.integration.Main.getMeAccount(Main.java:13)
	at com.callfire.api.client.integration.Main.main(Main.java:23)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)
Caused by: javax.net.ssl.SSLProtocolException: handshake alert:  unrecognized_name
	at sun.security.ssl.ClientHandshaker.handshakeAlert(ClientHandshaker.java:1410)
</pre>

this is temporary SSL configuration issue, please set jsse.enableSNIExtension property to false.
To do that set system propery:
 - System.setProperty("jsse.enableSNIExtension", "false");
 - or -Djsse.enableSNIExtension=false
