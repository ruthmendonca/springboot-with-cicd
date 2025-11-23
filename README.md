# springboot-mpb

Projeto exemplo Spring Boot com tema: músicas de MPB. Este projeto contém uma API simples com rotas para listar, buscar e criar músicas, testes unitários e um workflow de CI/CD no GitHub Actions que executa testes e gera o artefato `.jar`.

## Funcionalidades

- API REST para músicas (tema: MPB)
- Seed inicial com 3 músicas
- Testes unitários com MockMvc cobrindo as rotas
- Workflow GitHub Actions que executa `mvn test` e `mvn package` e publica o `.jar` como artifact

## Endpoints

- GET /api/musicas  — lista todas as músicas
- GET /api/musicas/{id} — obtém música por id
- GET /api/musicas/artista/{artista} — filtra por artista (URL-encode espaços)
- POST /api/musicas — cria nova música (JSON)

Exemplo de payload para POST:

```json
{ "titulo": "Olha", "artista": "Caetano Veloso", "ano": 1993 }
```

## CI/CD (GitHub Actions)

O workflow `.github/workflows/ci-cd.yml` faz:

- checkout do repo
- setup Java 17 (Temurin)
- cache do repositório Maven
- `mvn -B test`
- `mvn -B package` (gera .jar)
- faz upload do `.jar` como artifact do job (nome: `mpb-jar`)

Isso garante que, em pushes/PRs para `main`, os testes rodem e o artefato seja produzido, neste caso sem uso de Docker.
