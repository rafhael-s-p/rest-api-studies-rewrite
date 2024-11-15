package com.studies.domain.localization.state;

public record StateSearchQuery(int page, int perPage, String terms, String sort, String direction) {
}