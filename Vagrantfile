# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

	config.vm.define "sr" do |sr|
		sr.vm.box = "ubuntu/precise64"
		sr.vm.hostname = "serviceregistry.dev"
		sr.vm.provision :shell, path: "sr-bootstrap.sh"
		sr.vm.network :private_network, ip:"192.168.42.23"
		sr.vm.network "forwarded_port", guest: 8080, host: 8083
		sr.vm.synced_folder "./", "/vagrant/servicereg"
		sr.vm.provider "virtualbox" do |vb|
		  vb.customize ["modifyvm", :id, "--memory", "512"]
		end
	end
	
	config.vm.define "serviceregdb" do |serviceregdb|
	    serviceregdb.vm.box = "ubuntu/precise64"
	    serviceregdb.vm.hostname = "servicereg.dev"
	    serviceregdb.vm.provision :shell, path: "db-bootstrap.sh"
	    serviceregdb.vm.network :private_network, ip:"192.168.42.24"
	    serviceregdb.vm.network "forwarded_port", guest: 27017, host: 27017
	    serviceregdb.vm.synced_folder "./", "/vagrant/servicereg"
	    serviceregdb.vm.provider "virtualbox" do |vr|
	      vr.customize ["modifyvm", :id, "--memory", "512"]
	    end
	end

end
