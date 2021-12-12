/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zorrillo.colonias.servicio;

import com.zorrillo.colonias.modelo.Order;
import com.zorrillo.colonias.repositorio.OrderRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ydiez
 */
@Service
public class UserOrder {
    
     @Autowired
    private OrderRepositorio orderRepositorio;

    public List<Order> getAll() {
        return orderRepositorio.getAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderRepositorio.getOrder(id);
    }
//     public Order create(Order order){
//        if (order.getId() == null){
//            return order;
//        } else {
//            return orderRepositorio.create(order);
//        }
//    }
//
//    public Order update(Order order){
//        if (order.getId() != null){
//            Optional<Order> dbOrder = orderRepositorio.getOrder(order.getId());
//            if (!dbOrder.isEmpty()) {
//
//                if (order.getId() != null) {
//                    dbOrder.get().setId(order.getId());
//                }
//
//                if (order.getRegisterDay() != null) {
//                    dbOrder.get().setRegisterDay(order.getRegisterDay());
//                }
//
//                if (order.getStatus() != null) {
//                    dbOrder.get().setStatus(order.getStatus());
//                }
//
//                if (order.getSalesMan() != null) {
//                    dbOrder.get().setSalesMan(order.getSalesMan());
//                }
//
//                if (order.getProducts() != null) {
//                    dbOrder.get().setProducts(order.getProducts());
//                }
//
//                if (order.getQuantities() != null) {
//                    dbOrder.get().setQuantities(order.getQuantities());
//                }
//                orderRepositorio.update(dbOrder.get());
//                return dbOrder.get();
//            } else {
//                return order;
//            }
//        } else {
//            return order;
//        }
//    }
//
//    public boolean delete(Integer id){
//        return getOrder(id).map(order -> {
//            orderRepositorio.delete(order);
//            return true;
//        }).orElse(false);
//    }
//
//    public List<Order> getOrderByZone(String zone){
//        return orderRepositorio.getOrderByZone(zone);
//    }

    public Order create(Order order) {
        
        //obtiene el maximo id existente en la coleccion
        Optional<Order> orderIdMaxima = orderRepositorio.lastUserId();
        
        //si el id de la orden que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
        if (order.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (orderIdMaxima.isEmpty())
                order.setId(1);
            //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo de la orden
            else
                order.setId(orderIdMaxima.get().getId() + 1);
        }
        
        Optional<Order> e = orderRepositorio.getOrder(order.getId());
        if (e.isEmpty()) {
            return orderRepositorio.create(order);            
        }else{
            return order;
        }        
    }

    public Order update(Order order) {

        if (order.getId() != null) {
            Optional<Order> orderDb = orderRepositorio.getOrder(order.getId());
            if (!orderDb.isEmpty()) {
                if (order.getStatus() != null) {
                    orderDb.get().setStatus(order.getStatus());
                }
                orderRepositorio.update(orderDb.get());
                return orderDb.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean delete(int id) {
        Boolean aBoolean = getOrder(id).map(order -> {
            orderRepositorio.delete(order);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    //Ordenes de pedido asociadas a los asesores de una zona
    public List<Order> findByZone(String zona) {
        return orderRepositorio.findByZone(zona);
    }
    
}
