FROM eclipse-temurin:17-alpine
#RUN addgroup -S laskapi && adduser -S laskapi -G laskapi
#USER laskapi:laskapi
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF/ app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.in2horizon.escore.EscoreApplication","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
EXPOSE 8080