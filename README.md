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

## Deploy para Docker Hub (branch dedicada)

Adicione a branch `docker-deploy` (ou altere o nome no workflow se preferir). Existe um workflow em `.github/workflows/docker-deploy.yml` que é executado somente em pushes para essa branch e faz o seguinte:

- compila o JAR dentro de um contêiner Maven (o job usa `container: maven:3.8.8-jdk-17`)
- empacota o JAR e o envia como artifact para o job seguinte
- o segundo job faz build da imagem Docker (usa o `Dockerfile` do repositório) e envia para o Docker Hub

Antes de usar, configure os segredos no repositório (Settings → Secrets):

- `DOCKERHUB_USERNAME` — seu usuário do Docker Hub
- `DOCKERHUB_TOKEN` — token ou senha do Docker Hub (recomendado criar um Access Token no Docker Hub)

Como executar:

1. Crie e troque para a branch local `docker-deploy`:

```bash
git checkout -b docker-deploy
git push -u origin docker-deploy
```

2. A cada push nessa branch o workflow será disparado e, se tudo passar (build + docker push), você terá as imagens publicadas em `DOCKERHUB_USERNAME/springboot-mpb:latest` e `DOCKERHUB_USERNAME/springboot-mpb:<sha>`.

Se quiser usar outro nome de branch, atualize a propriedade `on.push.branches` em `.github/workflows/docker-deploy.yml`.