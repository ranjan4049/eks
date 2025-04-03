# Use a lightweight Alpine Linux base image
FROM alpine:latest

# Install necessary packages
RUN apk add --no-cache \
    curl \
    unzip \
    wget

# Install Terraform
ARG TERRAFORM_VERSION=1.11.1
RUN wget https://releases.hashicorp.com/terraform/${TERRAFORM_VERSION}/terraform_${TERRAFORM_VERSION}_linux_amd64.zip && \
    unzip terraform_${TERRAFORM_VERSION}_linux_amd64.zip -d /usr/local/bin && \
    rm terraform_${TERRAFORM_VERSION}_linux_amd64.zip
    


# Clean up
RUN rm -rf /var/cache/apk/*

# Set working directory
RUN mkdir -p /terraform
WORKDIR /terraform

# Default command
CMD ["/usr/local/bin/terraform"]
