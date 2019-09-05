FROM openjdk:14-alpine

ENV JAR_NAME codeshelf-tool-eslint-0.0.1-SNAPSHOT.jar \
    ESLINT_VERSION=6.3.0 \
    ESLINT_GOOGLE=0.14.0 \
    PATH=/usr/lib/node_modules/.bin:$PATH

RUN apk add --update npm

RUN npm install -g eslint@${ESLINT_VERSION} \
                   eslint-config-google@${ESLINT_GOOGLE} && \
    rm -rf /usr/share/man /tmp/* \
           /root/.npm /root/.node-gyp \
           /usr/lib/node_modules/npm/man \
           /usr/lib/node_modules/npm/doc \
           /usr/lib/node_modules/npm/html && \
    find /usr/lib/node_modules/npm -name test -o -name .bin -type d | xargs rm -rf

COPY ./build/libs/codeshelf-tool-eslint-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-jar", "/app/codeshelf-tool-eslint-0.0.1-SNAPSHOT.jar"]
