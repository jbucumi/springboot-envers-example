/**
 * 
 */
package tech.inkware.springenvers.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;

//@formatter:off
/**
 * @author jeanclaude.bucumi
 * jeanclaude.bucumi@outlook.com
 */
//@formatter:on

@NoRepositoryBean
public interface CoreRepository<T extends CoreEntity<String>> extends JpaRepository<T, String>, RevisionRepository<T, String, Integer>{

}
