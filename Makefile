build_gateway:
	docker build -f gateway/Dockerfile -t sbg ./gateway

build_solaris_mock:
	docker build -f solaris-mock-server/Dockerfile -t sms ./solaris-mock-server

run_gateway:
	docker run sbg:latest

run_gateway:
	docker run sms:latest

test_integration:
	docker-compose -f integration-tests/docker-compose.yml build --no-cache && \
	docker-compose -f integration-tests/docker-compose.yml up --abort-on-container-exit