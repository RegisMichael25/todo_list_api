# 📝 Todo List API

## Descrição
API REST para gerenciamento de lista de tarefas (Todo List) desenvolvida com Spring Boot. O projeto utiliza armazenamento em memória (ArrayList) para persistir as tarefas durante a execução da aplicação.

## 🚀 Funcionalidades
- Criar novas tarefas
- Listar todas as tarefas
- Buscar tarefa específica por código
- Atualizar tarefas existentes
- Marcar tarefas como concluídas
- Excluir tarefas

## 🛠️ Tecnologias Utilizadas
- Java 17
- Spring Boot 3.4.5
- Spring Web
- Spring DevTools
- Maven

## 📋 Pré-requisitos
- JDK 17+
- Maven 3.6+

## ⚙️ Como executar

### Clonando o repositório
```bash
git clone https://github.com/RegisMichael25/todo-list.git
cd todo-list
```

### Compilando e executando o projeto
```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## 📚 API Endpoints

### Tarefas (Task)

#### Listar todas as tarefas
```
GET /task
```
Resposta:
```json
[
  {
    "codigo": "550e8400-e29b-41d4-a716-446655440000",
    "titulo": "Implementar tela de cadastro",
    "concluida": false
  },
  {
    "codigo": "550e8400-e29b-41d4-a716-446655441111",
    "titulo": "Implementar tela de listagem",
    "concluida": true
  }
]
```

#### Obter tarefa por código
```
GET /task/{codigo}
```
Resposta:
```json
{
  "codigo": "550e8400-e29b-41d4-a716-446655440000",
  "titulo": "Implementar tela de cadastro",
  "concluida": false
}
```

#### Criar nova tarefa
```
POST /task
```
Corpo da requisição:
```json
{
  "titulo": "Criar testes unitários",
  "concluida": false
}
```
Resposta (HTTP 201 Created):
```json
{
  "codigo": "550e8400-e29b-41d4-a716-446655442222",
  "titulo": "Criar testes unitários",
  "concluida": false
}
```

#### Atualizar tarefa
```
PUT /task/{codigo}
```
Corpo da requisição:
```json
{
  "titulo": "Implementar tela de login",
  "concluida": false
}
```
Resposta:
```json
{
  "codigo": "550e8400-e29b-41d4-a716-446655440000",
  "titulo": "Implementar tela de login",
  "concluida": false
}
```

#### Marcar tarefa como concluída
```
PATCH /task/{codigo}/concluida
```
Corpo da requisição:
```json
{
  "concluida": true
}
```
Resposta:
```json
{
  "codigo": "550e8400-e29b-41d4-a716-446655440000",
  "titulo": "Implementar tela de cadastro",
  "concluida": true
}
```

#### Excluir tarefa
```
DELETE /task/{codigo}
```
Resposta: HTTP 204 No Content

## 📁 Estrutura do Projeto
```
src/
├── main/
│   ├── java/
│   │   └── br/
│   │       └── com/
│   │           └── curso/
│   │               └── todo_list/
│   │                   ├── TodoListApplication.java
│   │                   ├── controller/
│   │                   │   └── TaskController.java
│   │                   └── model/
│   │                       └── Task.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── br/
            └── com/
                └── curso/
                    └── todo_list/
                        └── TodoListApplicationTests.java
```

## 🧪 Implementação Atual

O projeto mantém uma lista de tarefas em memória usando um `ArrayList` dentro do `TaskController`:

```java
@RestController
@RequestMapping("/task")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        // Inicialização com tarefas de exemplo
        Task t1 = new Task();
        t1.setCodigo(UUID.randomUUID().toString());
        t1.setTitulo("Implementar tela de cadastro");
        t1.setConcluida(false);
        tasks.add(t1);

        Task t2 = new Task();
        t2.setCodigo(UUID.randomUUID().toString());
        t2.setTitulo("Implementar tela de listagem");
        t2.setConcluida(true);
        tasks.add(t2);
    }
    
    // Endpoints e operações CRUD...
}
```

A classe `Task` possui a seguinte estrutura:

```java
public class Task {
    private String codigo;
    private String titulo;
    private Boolean concluida;
    
    // Getters e Setters
}
```

Características importantes:
- Cada tarefa é identificada por um código UUID gerado automaticamente
- As operações CRUD são implementadas diretamente no controller
- Os dados são mantidos apenas em memória e são perdidos quando a aplicação é reiniciada

## 🔄 Próximos Passos

- Extrair a lógica do controller para uma classe de serviço
- Implementar persistência com banco de dados (JPA/Hibernate)
- Adicionar validação de dados
- Implementar tratamento de exceções mais robusto
- Criar interface de usuário (frontend)
- Adicionar mais campos ao modelo Task (descrição, data de criação, data de conclusão)
- Implementar busca e filtros

## 🧪 Testes
Para executar os testes:
```bash
mvn test
```

## 👨‍💻 Desenvolvimento
Este projeto foi criado como parte de um curso para praticar o desenvolvimento de APIs RESTful usando Spring Boot.

## 📄 Licença
Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
