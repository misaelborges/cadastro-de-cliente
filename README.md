# 📋 Cadastro de Cliente - API REST Simples

Este é um projeto didático para demonstrar os conceitos fundamentais de uma **API REST** usando **Spring Boot**. O objetivo é ensinar de forma prática o que são e como funcionam os principais componentes de uma aplicação Spring.

## 🎯 Objetivo do Projeto

Demonstrar na prática os 4 pilares fundamentais de uma API REST com Spring Boot:
- **Entity** (Entidade)
- **Repository** (Repositório)
- **Service** (Serviço)
- **Controller** (Controlador)

## 🏗️ Arquitetura do Projeto

```
src/main/java/com/senac/cadastro_de_cliente/
├── model/
│   └── Cliente.java          # 🏛️ ENTIDADE
├── repositories/
│   └── ClienteRepository.java # 🗄️ REPOSITORY
├── services/
│   └── ClienteService.java   # ⚙️ SERVICE
└── controllers/
    └── ClienteController.java # 🌐 CONTROLLER
```

## 📚 Conceitos Explicados

### 🏛️ **Entity (Entidade)**
**Arquivo:** `Cliente.java`

A **Entidade** representa uma tabela no banco de dados. É onde definimos:
- Os campos que serão colunas na tabela
- As anotações JPA para mapeamento
- Construtores e métodos getter/setter

```java
@Entity(name = "tbl_cliente")  // Nome da tabela
@Table(name = "tbl_cliente")
public class Cliente {
    @Id                                    // Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
    private Long id;
    
    @Column(nullable = false)              // Coluna obrigatória
    private String nome;
    
    @Column(nullable = false)
    private String sobrenome;
}
```

### 🗄️ **Repository (Repositório)**
**Arquivo:** `ClienteRepository.java`

O **Repository** é responsável pela comunicação direta com o banco de dados. Com Spring Data JPA, herdamos métodos prontos como `save()`, `findAll()`, `findById()`, etc.

```java
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Herda automaticamente métodos como:
    // - save() - salvar
    // - findAll() - buscar todos
    // - findById() - buscar por ID
    // - delete() - deletar
}
```

### ⚙️ **Service (Serviço)**
**Arquivo:** `ClienteService.java`

O **Service** contém as regras de negócio da aplicação. É onde implementamos:
- Validações
- Lógica de negócio
- Tratamento de dados antes de salvar/buscar

```java
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvarCliente(Cliente cliente) {
        // 🔍 VALIDAÇÃO DE REGRA DE NEGÓCIO
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new RuntimeException("Cliente não pode ser salvo");
        }
        return clienteRepository.save(cliente);
    }
}
```

### 🌐 **Controller (Controlador)**
**Arquivo:** `ClienteController.java`

O **Controller** é responsável por receber as requisições HTTP e devolver as respostas. É a "porta de entrada" da nossa API.

```java
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping              // GET /clientes
    @GetMapping("/{id}")     // GET /clientes/1
    @PostMapping("/salvar")  // POST /clientes/salvar
}
```

## 🚀 Como Funciona o Fluxo

```
1. 🌐 Cliente faz requisição HTTP → Controller
2. 🎮 Controller chama o → Service  
3. ⚙️ Service aplica regras de negócio e chama o → Repository
4. 🗄️ Repository acessa o banco de dados
5. 📤 Resposta volta: Repository → Service → Controller → Cliente
```

## 🛠️ Tecnologias Utilizadas

- **Java** - Linguagem de programação
- **Spring Boot** - Framework para criar aplicações Java
- **Spring Data JPA** - Para acesso ao banco de dados
- **PostgreSQL** - Banco de dados
- **Maven** - Gerenciador de dependências
- **Lombok** - Para reduzir código boilerplate (getters/setters)

## 📋 Pré-requisitos

- Java 17+
- PostgreSQL instalado e rodando
- Maven
- IDE (IntelliJ, Eclipse, VS Code)

## ⚙️ Configuração do Banco

No arquivo `application.properties`:

```properties
# Configuração do banco PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/cliente
spring.datasource.username=postgres
spring.datasource.password=admin

# JPA cria automaticamente as tabelas
spring.jpa.hibernate.ddl-auto=update
```

## 🚀 Como Executar

1. Clone o repositório
2. Configure o PostgreSQL com as credenciais do `application.properties`
3. Execute a aplicação
4. A API estará disponível em: `http://localhost:8080`

## 📡 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/clientes` | Lista todos os clientes |
| `GET` | `/clientes/{id}` | Busca cliente por ID |
| `POST` | `/clientes/salvar` | Cadastra um novo cliente |

### Exemplo de uso com cURL:

**Listar todos os clientes:**
```bash
curl -X GET http://localhost:8080/clientes
```

**Buscar cliente por ID:**
```bash
curl -X GET http://localhost:8080/clientes/1
```

**Cadastrar novo cliente:**
```bash
curl -X POST http://localhost:8080/clientes/salvar \
  -H "Content-Type: application/json" \
  -d '{"nome":"João","sobrenome":"Silva"}'
```

## 🎓 Para Estudar Mais

Este projeto é uma base sólida para entender:
- ✅ Como funciona uma API REST
- ✅ Arquitetura em camadas
- ✅ Injeção de dependência com `@Autowired`
- ✅ Mapeamento objeto-relacional com JPA
- ✅ Anotações do Spring Boot

**Próximos passos sugeridos:**
- Implementar DTOs (Data Transfer Objects)
- Adicionar validações com Bean Validation
- Usar ResponseEntity para controle de status HTTP
- Implementar tratamento de exceções
- Adicionar endpoints para UPDATE e DELETE

## 👨‍💻 Autor

Projeto criado para fins educacionais - Demonstração dos conceitos básicos do Spring Boot.

---
*Este é um projeto simples e didático. Em projetos reais, considere implementar DTOs, validações mais robustas, tratamento de exceções e testes unitários.*
