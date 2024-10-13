package ec.idat.rest.productos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.idat.rest.productos.model.Producto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/productos")
public class ProductoController {
    private final List<Producto> productos = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong();

    public ProductoController(){
        initData();
    }
    private void initData(){
        Producto laptop = new Producto(1L, "Laptop", "Laptop de alta gama AMD Ryzen 7 770x", 3200.50, 5);
        Producto celular = new Producto(2L, "Smartphone", "Galaxy S24 Ultra", 4599.99, 10);
        Producto monitor = new Producto(3L, "Monitor", "Monitor Samsung 4K", 1199.00, 7);
        productos.add(laptop);
        productos.add(celular);
        productos.add(monitor);
        contador.set(productos.stream().mapToLong(Producto::getId).max().orElse(0L));
    }
    //Listar todos los productos
    @GetMapping("/")
    public ResponseEntity<List<Producto>> listar() {
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    //Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable long id) {
        Producto producto = productos.stream().filter(p ->p.getId() == id).findAny().orElse(null);
        if (producto !=null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    //Registrar un nuevo producto
    @PostMapping()
    public ResponseEntity<Producto> registrar(@RequestBody Producto producto) {
        Producto productoNuevo= new Producto(contador.incrementAndGet(),
        producto.getNombre(),
        producto.getDescription(),
        producto.getPrecio(),
        producto.getCantidad());
        productos.add(productoNuevo);
        return new ResponseEntity<>(productoNuevo, HttpStatus.CREATED);
    }
    //Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable long id, @RequestBody Producto p) {
        Producto productoActualizado = null;
        for(Producto producto: productos){
            if (producto.getId() == id) {
                producto.setNombre(p.getNombre());
                producto.setDescription(p.getDescription());
                producto.setPrecio(p.getPrecio());
                producto.setCantidad(p.getCantidad());
                break;
            }
        }
        return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
    }
    // Eliminar Producto
    @DeleteMapping("/{id}")
    private ResponseEntity<Producto> eliminar(@PathVariable long id){
        Producto producto = productos.stream().filter(p ->p.getId() == id).findAny().orElse(null);
        if (producto != null) {
            productos.remove(producto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
