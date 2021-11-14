package com.pi4.ecommerce.DesignPatterns;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
   // Carrinho
    private List<Component> lista = new ArrayList();

    public Composite(){
    }
    
    public void adicionar(Component p){
        lista.add(p);
    }
    
    public void remover(Component p){
        lista.remove(p);
    }
    
    @Override
    public double getPreco() {
        double total = 0;
        for (Component p : lista) {
            total += p.getPreco();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Composite{" + "lista=" + lista + '}';
    }
    
    
    

}
