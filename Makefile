.PHONY: check run test lines md default jacoco spotless kover diktat cloc jar repo api

# Run detekt + ktlint
check:
	./gradlew detekt --profile --daemon

ktlint:
	./gradlew ktlintCheck

# Run spotless, more info: https://github.com/diffplug/spotless
spotless:
	./gradlew spotlessApply spotlessCheck

# Update the README.md file in accordance with the detekt report
md:
	sh scripts/update_kotlin_badge.sh && truncate -s0 README.md && cat config/main.md >> README.md && cat build/reports/detekt/metrics.md >> README.md && cat build/reports/detekt/complexity.md >> README.md

# Copy jacoco report
jacoco:
	cp -r build/reports/jacoco/test/html jacocoReport

# Run code style check + update the README.md file in accordance with the detekt report
default:
	make spotless && make check && make repo && make md && ./gradlew apiDump && ./gradlew apiCheck

# Build the project
run:
	./gradlew build

# Run tests
test:
	./gradlew test

# Print Kotlin lines count
lines:
	find . -name '*.kt' | xargs wc -l

cloc:
	cloc --include-lang=kotlin src/main

kover:
	./gradlew koverHtmlReport

diktat:
	./gradlew diktatCheck

jar:
	./gradlew shadowJar && mv ./build/libs/*.jar config/

repo:
	./gradlew detektReportToMdTask

api:
	./gradlew apiDump

.DEFAULT_GOAL := default
