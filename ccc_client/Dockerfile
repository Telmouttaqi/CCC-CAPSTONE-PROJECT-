FROM node:16-alpine as builder
WORKDIR /home/app
COPY . .
RUN npm ci && npm cache clean --force
RUN npm run build

# NEW CODE
# 1. Nginx & Alpine
FROM nginx:1.21-alpine
ENV API_URL=http://localhost:8080/api
# 2. replace nginx's default config with our nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY 85-set-environment-vars.sh /docker-entrypoint.d/
RUN chmod +x /docker-entrypoint.d/85-set-environment-vars.sh
 
# 3. copy build output from builder to the default static file location
COPY --from=builder /home/app/build /usr/share/nginx/html
EXPOSE 80
# 4. start nginx in the foreground
CMD ["nginx", "-g", "daemon off;"]