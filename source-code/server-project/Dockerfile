# Use an official Python runtime as a parent image
FROM python:3.9-slim-buster

# Set environment varibles
ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1

# Set work directory
WORKDIR /app

# Install pipenv
RUN pip install pipenv

# Install wget and unzip and uuid-runtime
RUN apt-get update && apt-get install -y wget unzip uuid-runtime curl

# Download ngrok
RUN wget -q -c -nc https://bin.equinox.io/c/bNyj1mQVY4c/ngrok-v3-stable-linux-amd64.zip -O ngrok.zip

# Install ngrok
RUN unzip ngrok.zip -d /app && rm ngrok.zip
RUN chmod +x /app/ngrok

# Copy Pipfile
COPY Pipfile Pipfile.lock ./

# Install application dependencies
RUN pipenv install --system --deploy

# Copy the current directory contents into the container at /app
COPY . /app

# Set ngrok authtoken
ARG NGROK_AUTH
RUN ./ngrok authtoken $NGROK_AUTH

# Expose port
EXPOSE 8000

# Generate a random UUID and use it as the subdomain for ngrok
RUN UUID=$(uuidgen) && echo "UUID=$UUID" > .env && echo "Generated UUID: $UUID"

# Run the command to start uWSGI and ngrok
#CMD uvicorn main:app --host 0.0.0.0 --port 8000 & ./ngrok http 8000
CMD uvicorn main:app --host 0.0.0.0 --port 8000 & ./ngrok http 8000