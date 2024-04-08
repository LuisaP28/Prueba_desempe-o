package entity;

public class Producto {
    private int id;
    private String nombre;
    private Double precio;
    private int id_tienda;
    private Tienda objTienda;

    public Producto() {
    }

    public Producto( String nombre, double precio, int id_tienda, Tienda objTienda) {
        this.nombre = nombre;
        this.precio = precio;
        this.id_tienda = id_tienda;
        this.objTienda = objTienda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }

    public Tienda getObjTienda() {
        return objTienda;
    }

    public void setObjTienda(Tienda objTienda) {
        this.objTienda = objTienda;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                "objtienda=" + objTienda +
                '}';
    }
}
