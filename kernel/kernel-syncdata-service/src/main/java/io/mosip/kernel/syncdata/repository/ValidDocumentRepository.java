package io.mosip.kernel.syncdata.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.mosip.kernel.core.dataaccess.spi.repository.BaseRepository;
import io.mosip.kernel.syncdata.entity.Title;
import io.mosip.kernel.syncdata.entity.ValidDocument;
import io.mosip.kernel.syncdata.entity.id.ValidDocumentID;

@Repository
public interface ValidDocumentRepository extends BaseRepository<ValidDocument, ValidDocumentID> {
	/**
	 * Method to find list of Title created , updated or deleted time is greater
	 * than lastUpdated timeStamp.
	 * 
	 * @param lastUpdated
	 *            timeStamp - last updated time stamp
	 * @param currentTimeStamp
	 *            - current time stamp
	 * @return list of {@link Title} - list of title
	 */
	@Query("FROM ValidDocument WHERE (createdDateTime > ?1 AND createdDateTime <=?2) OR (updatedDateTime > ?1 AND updatedDateTime<=?2)  OR  (deletedDateTime > ?1 AND deletedDateTime <=?2)")
	List<ValidDocument> findAllLatestCreatedUpdateDeleted(LocalDateTime lastUpdated, LocalDateTime currentTimeStamp);
}