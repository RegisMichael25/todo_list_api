package br.com.curso.todo_list.controller;

import br.com.curso.todo_list.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
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

    @GetMapping
    public ResponseEntity<List<Task>> listarTodos(){
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Task> procurarPorCod(@PathVariable String codigo) {

        for(Task task : tasks) {
            if (task.getCodigo().equals(codigo)) {

                return new ResponseEntity<>(task, HttpStatus.OK);

            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<Task> criar(@RequestBody Task task){

        task.setCodigo(UUID.randomUUID().toString());
        tasks.add(task);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Task> atualizarTask(@PathVariable String codigo, @RequestBody Task taskAtualizada) {

        for(Task taskAtual : tasks) {
            if (taskAtual.getCodigo().equals(codigo)){
                taskAtual.setTitulo(taskAtualizada.getTitulo());
                taskAtual.setConcluida(taskAtualizada.getConcluida());
                return new ResponseEntity<>(taskAtual, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Task> deleteTask(@PathVariable String codigo){
        Task taskRemover = null;

        for(Task task : tasks) {
            if(task.getCodigo().equals(codigo)){
                taskRemover = task;
            }
        }

        if (taskRemover != null) {
            tasks.remove(taskRemover);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{codigo}/concluida")
    public ResponseEntity<Task> atualizarConcluida(@PathVariable String codigo, @RequestBody Task taskAtualizada) {

        for(Task taskAtual : tasks) {
            if (taskAtual.getCodigo().equals(codigo)){
                taskAtual.setConcluida(taskAtualizada.getConcluida());
                return new ResponseEntity<>(taskAtual, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
