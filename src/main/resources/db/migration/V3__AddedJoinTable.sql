CREATE TABLE actor_movie (
    actor_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    CONSTRAINT fk_actor_id FOREIGN KEY(actor_id) REFERENCES actor(id),
    CONSTRAINT fk_movie_id FOREIGN KEY(movie_id) REFERENCES movie(id)
);