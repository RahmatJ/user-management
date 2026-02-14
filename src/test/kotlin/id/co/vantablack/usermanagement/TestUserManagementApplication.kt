package id.co.vantablack.usermanagement

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<UserManagementApplication>().with(TestcontainersConfiguration::class).run(*args)
}
