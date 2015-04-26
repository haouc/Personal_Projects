//
//  BookMarkTableViewCell.h
//  Google_News
//
//  Created by Hao Zhou on 2/16/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BookMarkTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *bookedTitleLabel;
@property (weak, nonatomic) IBOutlet UILabel *bookedDateLabel;
@property (weak, nonatomic) IBOutlet UILabel *bookedSnippetLabel;

@end
