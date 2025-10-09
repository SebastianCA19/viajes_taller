package com.demo.reservas.model;

public class Reservation {
    private Long id;
    private String tipo;     // hotel o vuelo
    private String cliente;
    private String destino;
    private String fecha;
    private String estado;   // pendiente, confirmada, cancelada

    public Reservation() {}

    public Reservation(Long id, String tipo, String cliente, String destino, String fecha, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.cliente = cliente;
        this.destino = destino;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public String getTipo() { return tipo; }
    public String getCliente() { return cliente; }
    public String getDestino() { return destino; }
    public String getFecha() { return fecha; }
    public String getEstado() { return estado; }

    public void setId(Long id) { this.id = id; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setEstado(String estado) { this.estado = estado; }
}
