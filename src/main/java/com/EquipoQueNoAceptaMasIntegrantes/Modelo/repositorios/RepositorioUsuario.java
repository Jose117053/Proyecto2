package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementación del inventario de usuarios, utilizando el patrón Singleton.
 * Gestiona un conjunto de usuarios.
 * 
 */
public class RepositorioUsuario implements Repositorio<Usuario> {

    private static volatile RepositorioUsuario uniqueInstance;
    private final List<Usuario> usuarios;

    /**
     * Obtiene la única instancia de InventarioUsuario.
     * 
     * @return La instancia única de InventarioUsuario.
     */
    public static RepositorioUsuario getInstance() {
        if (uniqueInstance == null) {
            synchronized (RepositorioUsuario.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new RepositorioUsuario();
                }
            }
        }
        return uniqueInstance;
    }

    // Constructor privado que inicializa la lista de usuarios.
    private RepositorioUsuario() {
        usuarios = crearUsuarios();
    }

    /**
     * Crea la lista de usuarios.
     * 
     * @return Lista con usuarios iniciales.
     */
    private List<Usuario> crearUsuarios() {
        List<Usuario> tempUsuarios;
        try {
            tempUsuarios = List.of(
                    new Usuario("luisa", "lu123", "Luisa Martinez"),
                    new Usuario("rafael", "raf123", "Rafael Martinez"),
                    new Usuario("samantha", "lu123", "Sam Martinez"));
        } catch (Exception e) {
            tempUsuarios = new ArrayList<>();
            System.err.println("Error inicializando usuarios: " + e.getMessage());
        }
        return tempUsuarios;
    }

    @Override
    public Collection<Usuario> findAll() {
        return usuarios;
    }

    @Override
    public Usuario find(Long PK) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(PK))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param username Nombre de usuario para buscar.
     * @return Usuario con el nombre de usuario dado, o null si no se encuentra.
     */
    public Usuario buscarPorUser(String username) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
