FROM node:latest AS build
WORKDIR /app
COPY frontend/package.json .
COPY frontend/package-lock.json .
RUN npm install
COPY frontend/public ./public
COPY frontend/src ./src
RUN npm run build

FROM nginx:latest
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]