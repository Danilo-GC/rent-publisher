FROM gcr.io/distroless/java:11

COPY /build/libs /app
WORKDIR /app

CMD ["rent-publisher-0.1-all.jar"]