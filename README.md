# Implementação das Métricas DORA em Ambientes de Nuvem

Este repositório contém o código-fonte desenvolvido como parte da dissertação de mestrado profissional intitulada **“Modelo de Avaliação da Maturidade DevOps em Instituições Financeiras”**.  
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

Ferramentas integradas:
- GitLab  
- Jenkins  
- SonarQube  
- JFrog  

---

## 🚀 Exemplos de Uso

### 1. Consultar tempo de entrega
```bash
curl -X GET "http://localhost:8080/tempoEntrega/namespace/meu-servico?data_inicio=2024-01-01&data_fim=2024-01-31" \
     -H "X-Access-Fontes-Token: <token>"
