# Use a base image with Node.js
FROM node:14 AS build

WORKDIR /app

# Copy the frontend source code to the container
COPY . .

# Install dependencies and build the frontend application
RUN npm install && npm run build --prod

# Copy the built frontend artifacts to the Apache web server directory
COPY --from=build /app/dist/cardiscover-frontend/ /sites/cardiscover.com/dist
