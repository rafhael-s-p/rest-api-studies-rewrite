package com.studies.domain.localization.state;

import com.studies.domain.pagination.Pagination;

import java.util.Optional;

public interface StateGateway {

    Pagination<State> findAll(StateSearchQuery aQuery);

    Optional<State> findById(StateID anId);

    State create(State aState);

    State update(State aState);

    void deleteById(StateID anId);

}