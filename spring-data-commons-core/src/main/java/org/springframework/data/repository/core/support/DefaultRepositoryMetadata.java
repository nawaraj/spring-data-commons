/*
 * Copyright 2011 the original author or authors.
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
 */
package org.springframework.data.repository.core.support;

import static org.springframework.core.GenericTypeResolver.*;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

/**
 * Default implementation of {@link RepositoryMetadata}. Will inspect generic types of
 * {@link Repository} to find out about domain and id class.
 * 
 * @author Oliver Gierke
 */
public class DefaultRepositoryMetadata implements RepositoryMetadata {

	private final Class<?> repositoryInterface;


	/**
	 * Creates a new {@link DefaultRepositoryMetadata} for the given repository
	 * interface.
	 *
	 * @param repositoryInterface
	 */
	public DefaultRepositoryMetadata(Class<?> repositoryInterface) {

		Assert.notNull(repositoryInterface);
		Assert.isTrue(Repository.class.isAssignableFrom(repositoryInterface));
		this.repositoryInterface = repositoryInterface;
	}


	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.support.RepositoryMetadata#getRepositoryInterface()
	 */
	public Class<?> getRepositoryInterface() {

		return repositoryInterface;
	}


	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.support.RepositoryMetadata#getDomainClass()
	 */
	public Class<?> getDomainClass() {
		
		Class<?>[] arguments =
				resolveTypeArguments(repositoryInterface, Repository.class);
		return arguments == null ? null : arguments[0];
	}


	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.support.RepositoryMetadata#getIdClass()
	 */
	public Class<?> getIdClass() {

		Class<?>[] arguments =
				resolveTypeArguments(repositoryInterface, Repository.class);
		return arguments == null ? null : arguments[1];
	}
}
