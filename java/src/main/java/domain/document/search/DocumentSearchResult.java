/*
 * StorageNotAvailableException.java is part of Document Manager (c) 2015.
 *
 * Document Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Document Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package domain.document.search;

import lombok.Data;

import java.util.List;

/**
 * @author Juan Camilo Rada
 */
@Data
public class DocumentSearchResult
{
    private String document;
    private String path;
    private float score;
    private List<String> fragments;
}
