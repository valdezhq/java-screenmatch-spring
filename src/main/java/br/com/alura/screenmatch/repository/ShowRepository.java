package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}