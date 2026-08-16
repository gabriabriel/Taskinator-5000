package com.taskinator5000_api.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.taskinator5000_api.entity.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final Resend resend;

    public EmailService(@Value("${resend.api-key}") String apiKey){
        this.resend = new Resend(apiKey);
    }

    public void sendReminderEmail(Task task, String userEmail){

        CreateEmailOptions params = CreateEmailOptions.builder().from("onboarding@resend.dev").to(userEmail)
                .subject("Lembrete: " + task.getTitle()).html("""
                    <h1>🔔 Lembrete de tarefa</h1>
                    
                    <h2>%s</h2>
                    
                    <p>%s</p>
                    
                    <p><strong>Data de vencimento:</strong> %s</p>
                    """.formatted(task.getTitle(), task.getDescription() != null ? task.getDescription() : "Sem descrição.", task.getDueDate() != null
                        ? task.getDueDate() : "Não definida.")).build();

        try {
            resend.emails().send(params);
        } catch (ResendException e){
            throw new RuntimeException("Erro ao enviar lembrete por e-mail",e);
        }
    }
}
