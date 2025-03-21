# Palmrq Layoff

## Requirement

* JDK 21

## Sample Configuration
### `local` profile
```yaml
app:
  kafka:
    bootstrap-servers: localhost:9092
  ollama:
    model: deepseek-r1:1.5b
    api-base: http://localhost:11434/api

me:
  ehp246:
    aufrest:
      restlogger:
        enabled: true
logging:
  config: classpath:log4j2-local.xml
 ```