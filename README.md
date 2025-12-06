# Implementação das Métricas **DORA (DevOps Research and Assessment)** em Ambientes de Nuvem

Este repositório contém o código-fonte desenvolvido como parte da dissertação de mestrado profissional intitulada **“Modelo de Avaliação DevOps em Ambientes de Nuvem com Métricas DORA: Um Estudo no Setor Financeiro”**.  
O artefato aqui disponibilizado representa um resultado científico, permitindo reuso, replicação e comparação em diferentes contextos organizacionais.

---

## 📌 Objetivo
Implementar e disponibilizar APIs para cálculo das métricas **DORA** em ambientes de nuvem, possibilitando a avaliação da maturidade **DevOps** em instituições financeiras de grande porte.

As métricas implementadas são:
- **Frequência de implantações**
- **Tempo de entrega para mudanças**

---

## 🏗️ Arquitetura das APIs
O código está organizado em camadas:
- **Camada de exposição (REST)**: Endpoints para consulta das métricas.
- **Camada de serviço**: Lógica de cálculo estatístico e integração com ferramentas.
- **Modelos de dados**: Estruturas para representar estatísticas e resultados.
---

## 🧩 Camadas do Projeto

### 1. **Camada REST (`rest/`)**
- Expõe os endpoints HTTP para consulta das métricas.
- Exemplos:
  - `GET /tempoEntrega/namespace/{service}` → estatísticas de tempo de entrega.
  - `GET /Entrega/namespace/{service}` → estatísticas de frequência de entrega.
  - `GET /tempoEntrega/performance/{service}` → rating de performance do tempo de entrega.
  - `GET /Entrega/frequencia_rating/{service}` → rating da frequência de entrega.

### 2. **Camada de Serviço (`service/`)**
- Contém a lógica de negócio e cálculo das métricas.
- `TempoEntregaService`: calcula estatísticas de tempo de entrega (média, mediana, desvio padrão).
- `FrequenciaEntregaService`: calcula estatísticas de frequência de entrega (semanal, mensal).
- Serviços auxiliares (`PerformanceService`, `PerformanceDescricaoService`) retornam ratings e regras de classificação.

### 3. **Camada de Modelos (`models/`)**
- Define os objetos de transferência de dados (DTOs).
- Exemplos:
  - `TempoEntregaEstatisticas`: estatísticas detalhadas de tempo de entrega.
  - `TempoEntregaPerformance`: rating do tempo de entrega.
  - `FrequenciasEntregaEstatisticas`: estatísticas de frequência de entrega.
  - `FrequenciaEntregaPerformance`: rating da frequência de entrega.

### 4. **Camada de Integração (`gitlab/`, `k8s/`, `dao/`)**
- Stubs que simulam integração com GitLab e Kubernetes.
- `GitLab`, `Commit`, `Deploy`, `Tag`: simulam dados de versionamento e deploys.
- `Cluster`, `ReplicaSet`: simulam namespaces e ambientes.
- `MsDao`: fornece lista de microsserviços de aplicações.

### 5. **Camada Utilitária**
- `Holiday`: cálculo de dias úteis.
- `MetricsException`: exceções customizadas.
- `TracedWithRequestID`: anotação para tracing.

---

## 🔧 Observações e Premissas

Este projeto contém **stubs** (implementações simuladas) para permitir compilação e testes locais.  
Para funcionamento adequado em ambiente real, é necessário configurar as integrações com ferramentas externas:

- **GitLab**  
  - Usado para coletar informações de commits, tags e deploys.  
  - Permite calcular métricas de tempo de entrega com base em histórico de versionamento.  

- **Kubernetes (Cluster / ReplicaSet)**  
  - Usado para identificar namespaces e ambientes de execução.  
  - Permite calcular métricas de frequência de entrega e tempo de deploy em produção.  

- **DAO de Microsserviços (MsDao)**  
  - Usado para mapear aplicações e serviços pelos seus microsserviços.
  - Permite agrupar estatísticas por aplicação ou conjunto de microsserviços.  

- **Holiday / Calendário de dias úteis**  
  - Usado para calcular tempo de entrega considerando apenas dias úteis.  
  - Permite métricas mais realistas, desconsiderando finais de semana e feriados.  

### Premissa
Sem essas integrações configuradas, os endpoints retornam dados simulados.  
Para uso em produção, configure os acessos às ferramentas (tokens de API, clusters Kubernetes, repositórios GitLab) conforme sua infraestrutura.

---

## 🚀 Exemplos de Uso

### 1. Consultar tempo de entrega
```bash
curl -X GET "http://localhost:8080/tempoEntrega/namespace/meu-servico?data_inicio=2024-01-01&data_fim=2024-01-31" \
     -H "X-Access-Fontes-Token: <token>"
