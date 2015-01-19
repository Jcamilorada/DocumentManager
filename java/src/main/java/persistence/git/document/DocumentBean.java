/*
 * DocumentBean.java is part of Document Manager (c) 2015.
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
 *
 * You should have received a copy of the GNU General Public License
 * along with Document Manager.  If not, see <http://www.gnu.org/licenses/>.
 */

package persistence.git.document;

import lombok.Data;

/**
 * @author Juan Camilo Rada
 *
 * Contains all the document information as name, content and path.
 */
@Data
public class DocumentBean
{
    private String name;
    private String path;
    private String id;
    private String content;
}
