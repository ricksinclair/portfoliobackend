package com.ulrictodman.portfoliobackend.service.util;

import com.ulrictodman.portfoliobackend.exception.NotFoundException;

public class GlobalHelperMethods {
    public static void notFoundExceptionHelper(int id, String s) {
        throw new NotFoundException(String.format(s, id));
    }
}