//
//  MyBankViewController.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 08.07.2022.
//

import UIKit

class MyBankViewController: UIViewController {

	let scrollView: UIScrollView = MyBankScrollView()
    	
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
	@objc func doNothing() {
		let vc = UIViewController()
		vc.view.backgroundColor = .systemPink
		navigationController?.pushViewController(vc, animated: true)
	}
	
	func configure() {
		view.addSubview(scrollView)
		scrollView.contentSize = CGSize(width: view.frame.width, height: view.frame.height-70)
		NSLayoutConstraint.activate([
			scrollView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
			scrollView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
			scrollView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			scrollView.bottomAnchor.constraint(equalTo: view.bottomAnchor)
		])
	}
}

