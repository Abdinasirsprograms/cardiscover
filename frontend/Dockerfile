# Use a base image with Node.js
FROM node:20-alpine AS frontend-build

WORKDIR /app

# Copy the frontend source code to the container
COPY package*.json .

# Install dependencies and build the frontend application
RUN npm install && npm run build --prod

# Production stage
FROM alpine:latest

# Create a target directory for the frontend artifacts outside of Docker
RUN mkdir -p /sites/cardiscover.com/dist

# Copy the built frontend artifacts to the Apache web server directory
COPY --from=frontend-build /app/cardiscover-frontend/dist/ /sites/cardiscover.com/dist
