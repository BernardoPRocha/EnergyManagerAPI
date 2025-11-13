# ⚡ EnergyManagerAPI

API desenvolvida para o projeto da disciplina **SOA & Web Services** (FIAP).  
O sistema implementa autenticação JWT, controle de acesso por perfis e endpoints REST seguros para o **gerenciamento de consumo de energia elétrica**.

---

## 👨‍💻 Integrantes do Grupo

| Nome | RM |
|------|----|
| Bernardo Pinto Rocha | 99209 |
| Pedro Palladino | 551180 |
| Renato Izumi | 99242 |

---

## LINK DO GITHUB 
- [Link para o Github](https://github.com/BernardoPRocha/EnergyManagerAPI.git)

---

## 🎯 Objetivo do Projeto

O **EnergyManagerAPI** tem como objetivo oferecer uma estrutura simples e escalável para gerenciamento de dados de consumo energético.  
Ele utiliza **Spring Boot + Spring Security + JWT** para implementar autenticação e autorização seguras, além de seguir o padrão arquitetural **SOA (Service-Oriented Architecture)**.

O projeto representa um exemplo de microsserviço que:
- Possui endpoints REST;
- Implementa autenticação via JWT;
- Mantém política de sessão `STATELESS`;
- Aplica boas práticas de modularização e reutilização de serviços.

---

## 🏗️ Estrutura do Projeto

```
src
 └── main
     └── java
         └── com
             └── gs
                 └── energy
                     ├── Application.java
                     ├── auth
                     │    ├── controller
                     │    │    └── AuthController.java
                     │    ├── model
                     │    ├── repository
                     │    └── service
                     ├── config
                     │    ├── SecurityConfig.java
                     │    ├── JwtAuthenticationFilter.java
                     │    └── JwtUtil.java
                     └── controller
                          └── EnergyController.java
```

---

## ⚙️ Tecnologias Utilizadas

- ☕ **Java 21**
- 🌱 **Spring Boot 3.2.5**
- 🔐 **Spring Security + JWT (0.11.5)**
- 🐬 **MySQL**
- 🧱 **Maven**
- 💾 **JPA / Hibernate**

---

## 🔑 Segurança e Autenticação

A aplicação utiliza **Spring Security com autenticação JWT**:

- Login via `/api/auth/login`;
- Tokens assinados com `HS256`;
- Sessões `STATELESS`;
- Controle de autorização baseado em **roles (ADMIN, USER)**;
- Filtros de segurança configurados manualmente via `SecurityConfig`.

---

## 🚀 Como Executar o Projeto

### 1️⃣ Clonar o Repositório
```bash
git clone https://github.com/BernardoPRocha/EnergyManagerAPI.git
cd EnergyManagerAPI
```

### 2️⃣ Configurar o Banco de Dados
Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/energy_db
spring.datasource.username=root
spring.datasource.password=fiap1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Executar o Projeto
```bash
mvn spring-boot:run
```

A aplicação iniciará em:  
➡️ **http://localhost:8080**

---

## 🧩 Endpoints da API

### 🔸 Autenticação
**POST** `/api/auth/login`

```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "username": "admin"
}
```

---

## 🧩 Fotos do MySQL 


<img width="487" height="398" alt="image" src="https://github.com/user-attachments/assets/ecffb33e-0f81-4fa7-b7e4-51d431447155" />
<img width="832" height="578" alt="image" src="https://github.com/user-attachments/assets/15f5bf41-d9f7-4b2a-a17d-3fc9918d8be3" />



### 🔸 Consumo de Energia (Protegido por JWT)

| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| GET | `/api/energy` | Lista todos os registros |
| POST | `/api/energy` | Adiciona novo registro |
| GET | `/api/energy/{id}` | Busca registro por ID |
| PUT | `/api/energy/{id}` | Atualiza registro |
| DELETE | `/api/energy/{id}` | Remove registro |

#### Exemplo de Cabeçalho
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc2MzAxMDg1NSwiZXhwIjoxNzYzMDQ2ODU1fQ.tnKvx9gGvfZJes91dDGhfPNQDYBobZVoalQBr2Wk-0s
```

#### Exemplo de `POST /api/energy`
```json
{
  "source": "Solar",
  "kWh": 120.5,
  "date": "2025-11-13"
}
```

---

## ⚖️ Critérios Atendidos

| Critério | Descrição | Peso | Status |
|-----------|------------|------|--------|
| Entities, VO, Enums, DTO, Controllers | Estrutura completa | 5% | ✅ |
| ResponseEntity padronizado | Implementado | 5% | ✅ |
| Tratamento global de exceções | Classe `Advice` | 10% | ✅ |
| Autenticação de usuário | Login JWT | 10% | ✅ |
| Autorização por perfil | Roles no UserDetails | 20% | ✅ |
| Política Stateless e JWT | Implementada | 20% | ✅ |
| Casos de uso e regras de negócio | Serviços isolados | 20% | ✅ |
| Organização modular e reuso | Padrão SOA | 10% | ✅ |

---

## 📚 Referências

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Reference](https://spring.io/projects/spring-security)
- [JJWT Library (io.jsonwebtoken)](https://github.com/jwtk/jjwt)
- [MySQL Connector/J](https://dev.mysql.com/doc/connector-j/8.0/en/)

---

## 🧠 Conclusão

O projeto **EnergyManagerAPI** demonstra a aplicação prática dos conceitos de **SOA**, **segurança de APIs REST**, e **autenticação JWT**.  
A arquitetura modular garante fácil expansão e integração com novos serviços ou microsserviços futuros — tornando-o escalável e didático.

---

## 🏁 Status do Projeto
✅ **Concluído e funcional**

```
Tomcat started on port(s): 8080
Application started successfully!
```
