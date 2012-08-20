/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * Sang-cheon Park	2012. 8. 17.		First Draft.
 */
package sample.common.pagination;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -7657916900376830596L;

    static {
        EMPTY_PAGE = new Page(Collections.EMPTY_LIST, 1, 0, "", "");
    }

    public static final Page EMPTY_PAGE;
    private Collection<?> objects;
    private int currentPage;
    private int totalCount;
    private int pageunit;
    private int pagesize;
    private int maxPage;
    private int beginUnitPage;
    private int endUnitPage;
    private String search;
    private String condition;

    /**
     * @return the objects
     */
    public Collection<?> Objects() {
        return objects;
    }

    /**
     * @param objects
     *            the objects to set
     */
    public void setObjects(Collection<?> objects) {
        this.objects = objects;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *            the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount
     *            the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return the pageunit
     */
    public int getPageunit() {
        return pageunit;
    }

    /**
     * @param pageunit
     *            the pageunit to set
     */
    public void setPageunit(int pageunit) {
        this.pageunit = pageunit;
    }

    /**
     * @return the pagesize
     */
    public int getPagesize() {
        return pagesize;
    }

    /**
     * @param pagesize
     *            the pagesize to set
     */
    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    /**
     * @return the maxPage
     */
    public int getMaxPage() {
        return maxPage;
    }

    /**
     * @param maxPage
     *            the maxPage to set
     */
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    /**
     * @return the beginUnitPage
     */
    public int getBeginUnitPage() {
        return beginUnitPage;
    }

    /**
     * @param beginUnitPage
     *            the beginUnitPage to set
     */
    public void setBeginUnitPage(int beginUnitPage) {
        this.beginUnitPage = beginUnitPage;
    }

    /**
     * @return the endUnitPage
     */
    public int getEndUnitPage() {
        return endUnitPage;
    }

    /**
     * @param endUnitPage
     *            the endUnitPage to set
     */
    public void setEndUnitPage(int endUnitPage) {
        this.endUnitPage = endUnitPage;
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search
     *            the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition
     *            the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Collection<?> getList() {
        return objects;
    }

    public void setList(Collection<?> collection) {
    }

    public boolean hasNextPage() {
        return currentPage < maxPage;
    }

    public boolean hasPreviousPage() {
        return currentPage > 1;
    }

    public int getNextPage() {
        return currentPage + 1;
    }

    public void setNextPage(int i) {
    }

    public int getPreviousPage() {
        return currentPage - 1;
    }

    public void setPreviousPage(int i) {
    }

    public boolean hasNextPageUnit() {
        return endUnitPage < maxPage;
    }

    public boolean hasPreviousPageUnit() {
        return currentPage >= pageunit + 1;
    }

    public int getStartOfNextPageUnit() {
        return endUnitPage + 1;
    }

    public int getStartOfPreviousPageUnit() {
        return beginUnitPage - 1;
    }

    public int getPageOfNextPageUnit() {
        return currentPage + pageunit >= maxPage ? maxPage : currentPage + pageunit;
    }

    public int getPageOfPreviousPageUnit() {
        return currentPage - pageunit <= 1 ? 1 : currentPage - pageunit;
    }

    public int getEndListPage() {
        return endUnitPage <= maxPage ? endUnitPage : maxPage;
    }

    public void setEndListPage(int i) {
    }

    public int getSize() {
        return objects.size();
    }

    public boolean isEmptyPage() {
        return objects == null || getSize() == 0;
    }

    public void setEmptyPage(boolean flag) {
    }

    public int getTotal() {
        return totalCount;
    }

    public void setTotal(int i) {
    }

    public String getCurrentPageStr() {
        return (new Integer(currentPage)).toString();
    }

    public void setCurrentPageStr(String s) {
    }

    public Page() {
        pageunit = 10;
        pagesize = 10;
        search = "";
        condition = "";
    }

    public Page(Collection<?> objects, int currentPage, int totalCount) {
        pageunit = 10;
        pagesize = 10;
        search = "";
        condition = "";
        this.objects = objects;
        this.totalCount = totalCount;
        maxPage = pagesize != 0 ? (totalCount - 1) / pagesize + 1 : this.totalCount;
        this.currentPage = currentPage <= maxPage ? currentPage : maxPage;
        beginUnitPage = ((currentPage - 1) / pageunit) * pageunit + 1;
        endUnitPage = beginUnitPage + (pageunit - 1);
    }

    public Page(Collection<?> objects, int currentPage, int totalCount, String condition, String search) {
        this(objects, currentPage, totalCount);
        this.condition = condition;
        this.search = search;
    }

    public Page(Collection<?> objects, int currentPage, int totalCount, int pageunit, int pagesize) {
        this.pageunit = 10;
        this.pagesize = 10;
        search = "";
        condition = "";
        if (pageunit <= 0 || pagesize <= 0) {
            throw new RuntimeException("Page unit or page size should be over 0.");
        } else {
            this.pageunit = pageunit;
            this.pagesize = pagesize;
            this.objects = objects;
            this.totalCount = totalCount;
            maxPage = pagesize != 0 ? (totalCount - 1) / pagesize + 1 : this.totalCount;
            this.currentPage = currentPage <= maxPage ? currentPage : maxPage;
            beginUnitPage = ((currentPage - 1) / pageunit) * pageunit + 1;
            endUnitPage = beginUnitPage + (pageunit - 1);
            return;
        }
    }
}
// end of Page.java