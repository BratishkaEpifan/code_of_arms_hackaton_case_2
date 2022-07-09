//
//  MyBankViewController.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 08.07.2022.
//

import UIKit

class MyBankViewController: UIViewController, GoToAppDelegate {

	let myBankView = MyBankView()
		
	override func viewDidLoad() {
		super.viewDidLoad()
		view.backgroundColor = .systemBackground
		configureNC()
		configure()
	}

	func configureNC() {
		let personImage = UIImage(systemName: "person.circle.fill")
		let button = UIBarButtonItem(image: personImage,
									 style: .plain,
									 target: self, action: #selector(doNothing))
		let bellImage = UIImage(systemName: "bell")
		let buttonN = UIBarButtonItem(image: bellImage,
									  style: .plain,
									  target: self, action: #selector(doNothing))
		let searchBar:UISearchBar = UISearchBar(frame: CGRect(x: 0, y: 0, width: 270, height: 20))
		searchBar.placeholder = "Поиск в Jusan"
		let leftNavBarButton = UIBarButtonItem(customView:searchBar)
		UINavigationBar.appearance().tintColor = .systemGray
		navigationItem.rightBarButtonItems = [button, buttonN]
		navigationItem.leftBarButtonItem = leftNavBarButton
	}
	func goToApp() {
		let vc = TabBarViewController()
		vc.hidesBottomBarWhenPushed = true
		navigationController?.pushViewController(vc, animated: true)
	}

	@objc func doNothing() {
		let vc = UIViewController()
		vc.view.backgroundColor = .systemPink
		navigationController?.pushViewController(vc, animated: true)
	}
	
	func configure() {
		myBankView.goToAppDelegate = self
		view.addSubview(myBankView)
		NSLayoutConstraint.activate([
			myBankView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
			myBankView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
			myBankView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			myBankView.bottomAnchor.constraint(equalTo: view.bottomAnchor)
		])
	}
}

