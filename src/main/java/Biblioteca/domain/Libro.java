package Biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data; 

@Data 
@Entity
@Table(name = "libro")
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "El título es obligatorio")
    @Size(max = 255)
    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @NotEmpty(message = "El autor es obligatorio")
    @Size(max = 200)
    @Column(name = "autor", nullable = false, length = 200)
    private String autor; 

    @Size(max = 20)
    @Column(name = "isbn", length = 20)
    private String isbn; 

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion; 

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "disponible", nullable = false)
    private Boolean disponible = true; 

    @DecimalMin(value = "0.00", message = "El precio no puede ser negativo")
    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio; 

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Relación ManyToOne: Muchos libros pertenecen a una categoría
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
    @PrePersist
    protected void onCreate() {
        
        this.createdAt = LocalDateTime.now(); 
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    

    public Long getId() {
        return id;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    
   public String getTitulo() {
        return titulo;
    }
    

    public String getAutor() {
        return autor;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    // También para el binding del formulario (si usas th:field="*{autor}" en el formulario de creación/edición)
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public BigDecimal getPrecio() {
    return precio;
}

// Y el setter, por si el formulario lo necesita para el binding:
public void setPrecio(BigDecimal precio) {
    this.precio = precio;
}
}