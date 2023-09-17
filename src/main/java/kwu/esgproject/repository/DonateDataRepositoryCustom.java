package kwu.esgproject.repository;

import kwu.esgproject.domain.Donate;

import java.util.List;

public interface DonateDataRepositoryCustom {
    List<Donate> findDonatesByCompanyId(Long companyId);
    List<Donate> findDonatesByUserId(Long userId);
    List<Donate> findDonatesByCompanyIdAndUserId(Long companyId, Long userId);
}
