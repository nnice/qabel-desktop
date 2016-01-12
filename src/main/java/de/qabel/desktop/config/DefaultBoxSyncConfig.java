package de.qabel.desktop.config;

import de.qabel.core.config.Account;
import de.qabel.core.config.Identity;

import java.nio.file.Path;
import java.util.Observable;

public class DefaultBoxSyncConfig extends Observable implements BoxSyncConfig {
	private static final String DEFAULT_NAME = "New Sync Config";
	private Path localPath;
	private Path remotePath;
	private Identity identity;
	private Account account;
	private Boolean paused = false;
	private String name;

	public DefaultBoxSyncConfig(Path localPath, Path remotePath, Identity identity, Account account) {
		this(DEFAULT_NAME, localPath, remotePath, identity, account);
	}

	public DefaultBoxSyncConfig(String name, Path localPath, Path remotePath, Identity identity, Account account) {
		this.name = name;
		this.localPath = localPath;
		this.remotePath = remotePath;
		this.identity = identity;
		this.account = account;
	}

	@Override
	public void setLocalPath(Path localPath) {
		if (!this.localPath.equals(localPath)) {
			setChanged();
		}
		this.localPath = localPath;
		notifyObservers(localPath);
	}

	@Override
	public Path getLocalPath() {
		return localPath;
	}

	@Override
	public Path getRemotePath() {
		return remotePath;
	}

	@Override
	public void setRemotePath(Path remotePath) {
		if (!this.remotePath.equals(remotePath)) {
			setChanged();
		}

		this.remotePath = remotePath;
		notifyObservers(remotePath);
	}

	@Override
	public void pause() {
		if (!isPaused()) {
			setChanged();
		}

		paused = true;
		notifyObservers();
	}

	@Override
	public void unpause() {
		if (isPaused()) {
			setChanged();
		}

		paused = false;
		notifyObservers();
	}

	@Override
	public boolean isPaused() {
		return paused;
	}

	@Override
	public Identity getIdentity() {
		return identity;
	}

	@Override
	public Account getAccount() {
		return account;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
