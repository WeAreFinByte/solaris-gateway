build_gateway:
	docker build -f gateway/Dockerfile -t sbg ./gateway

run_gateway:
	docker run sbg:latest

test_integration:
	docker-compose -f integration-tests/docker-compose.yml build --no-cache && \
	docker-compose -f integration-tests/docker-compose.yml up --abort-on-container-exit