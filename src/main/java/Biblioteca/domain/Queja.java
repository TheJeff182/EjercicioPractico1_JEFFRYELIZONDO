package Biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import Biblioteca.domain.TipoQueja; 

@Data
@Entity
@Table(name = "queja")
public class Queja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    

    @Size(max = 150)
    @Column(name = "nombre_cliente", length = 150)
    private String nombreCliente;

  
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    @Email(message = "Debe ser un email válido")
    @Size(max = 200)
    @Column(name = "email", length = 200)
    private String email;

   
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Size(max = 30)
    @Column(name = "telefono", length = 30)
    private String telefono;

 
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoQueja tipo;

   
    public TipoQueja getTipo() { return tipo; }
    public void setTipo(TipoQueja tipo) { this.tipo = tipo; }

    @Size(max = 200)
    @Column(name = "asunto", length = 200)
    private String asunto;

  
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    @NotEmpty(message = "El mensaje no puede estar vacío")
    @Column(name = "mensaje", columnDefinition = "TEXT", nullable = false)
    private String mensaje;


    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }



    @Column(name = "tratado", nullable = false)
    private Boolean tratado = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

   
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}