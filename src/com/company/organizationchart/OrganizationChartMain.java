package com.company.organizationchart;

public class OrganizationChartMain {

    OrganizationRepository organizationRepository;

    /**
     * 하위 조직이 있는지 체크한다.
     */
    public boolean isChildOrganizationExist(Organization organization) {
        return organization.directChildOrganization.size() > 0;
    }

    /**
     * 새로운 조직을 생성한다.
     */
    public void createNewOrganization(Organization parentOrganization, String organizationName) {
        int newDepth = ++parentOrganization.currentDepth;
        Organization organization = new Organization(parentOrganization, organizationName,
            newDepth);

        organizationRepository.saveOrganization(organization);
    }


    /**
     * 조직명을 수정한다.
     */
    public void updateOrganizationName(Organization organization, String newName) {
        organization.setOrganizationName(newName);
        organizationRepository.saveOrganization(organization);
    }

    /**
     * 조직을 삭제한다.
     */
    public void deleteOrganization(Organization organization) {
        if (!isChildOrganizationExist(organization)) {
            organizationRepository.deleteOrganization(organization);
        }
    }

    public void execute() {
        // 1. 조직을 추가한다. -> 조직도 클래스가 필요하다.
        Organization organization = new Organization(null, "개발부", 1);
        createNewOrganization(organization, "백엔드1cell");

        // 2. 조직명을 수정한다.
        updateOrganizationName(organization, "Backend 2 cell");

        // 3. 하위 조직을 생성한다.
        createNewOrganization(organization, "Child 1 Cell");

        // 4. 하위 조직을 삭제한다.
        deleteOrganization(organization);
    }


}
