package ec.idat.rest.productos.model;

public class Producto {
    private long id;
    private String nombre;
    private String description;
    private Double precio;
    private int cantidad;

    public Producto(long id, String nombre, String description, Double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.description = description;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}