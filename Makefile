.PHONY: check run test lines
check:
	./gradlew spotlessApply spotlessCheck spotlessKotlin detekt ktlintCheck --profile --daemon && truncate -s0 README.md && cat config/main.md >> README.md && cat build/reports/detekt/detekt.md >> README.md

run:
	./gradlew build

test:
	./gradlew test

lines:
	find . -name '*.kt' | xargs wc -l

.DEFAULT_GOAL := check
